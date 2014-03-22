package org.brongus.shapefile;

import junit.framework.TestCase;
import org.brongus.shapefile.files.dbf.DBF_Field;
import org.brongus.shapefile.files.dbf.DBF_File;
import org.brongus.shapefile.files.shp.SHP_File;
import org.brongus.shapefile.files.shp.shapeTypes.ShpPolygon;
import org.brongus.shapefile.files.shp.shapeTypes.ShpShape;
import org.brongus.shapefile.files.shx.SHX_File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by lazar on 3/22/14.
 */
@RunWith(JUnit4.class)
public class ShapeFileTest extends TestCase {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void TestWorks() {

    }

    @Test
    public void BadShapefileTest() throws Exception
    {
        DBF_File.LOG_INFO           = !false;
        DBF_File.LOG_ONLOAD_HEADER  = false;
        DBF_File.LOG_ONLOAD_CONTENT = false;

        SHX_File.LOG_INFO           = !false;
        SHX_File.LOG_ONLOAD_HEADER  = false;
        SHX_File.LOG_ONLOAD_CONTENT = false;

        SHP_File.LOG_INFO           = !false;
        SHP_File.LOG_ONLOAD_HEADER  = false;
        SHP_File.LOG_ONLOAD_CONTENT = false;


        try {
            // GET DIRECTORY
            String curDir = System.getProperty("user.dir");

            // LOAD SHAPE FILE (.shp, .shx, .dbf)
            ShapeFile shapefile = new ShapeFile(curDir+"/data/shapefiles/usa", "states").READ();

            // TEST: printing some content
            ShpShape.Type shape_type = shapefile.getSHP_shapeType();
            System.out.println("\nshape_type = " +shape_type);

            int number_of_shapes = shapefile.getSHP_shapeCount();
            int number_of_fields = shapefile.getDBF_fieldCount();

            for(int i = 0; i < number_of_shapes; i++){
                ShpPolygon shape    = shapefile.getSHP_shape(i);
                String[] shape_info = shapefile.getDBF_record(i);

                ShpShape.Type type     = shape.getShapeType();
                int number_of_vertices = shape.getNumberOfPoints();
                int number_of_polygons = shape.getNumberOfParts();
                int record_number      = shape.getRecordNumber();

                System.out.printf("\nSHAPE[%2d] - %s\n", i, type);
                System.out.printf("  (shape-info) record_number = %3d; vertices = %6d; polygons = %2d\n", record_number, number_of_vertices, number_of_polygons);

                for(int j = 0; j < number_of_fields; j++){
                    String data = shape_info[j].trim();
                    DBF_Field field = shapefile.getDBF_field(j);
                    String field_name = field.getName();
                    System.out.printf("  (dbase-info) [%d] %s = %s", j, field_name, data);
                }
                System.out.printf("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
