package org.brongus.shapefile.bcross;

import org.brongus.shapefile.ShapeFile;
import org.brongus.shapefile.files.shp.shapeTypes.ShpPolygon;
import org.brongus.shapefile.files.shp.shapeTypes.ShpShape;

import java.util.ArrayList;

/**
 * Created by lazar on 3/22/14.
 */
public class State {

    private static final ShpShape.Type STATE_SHAPEFILE_TYEPE = ShpShape.Type.Polygon;

    private ShapeFile shapeFile;

    private ArrayList<ShpPolygon> stateShapes;
    private ArrayList<String[]> stateInfos;


    public State() throws Exception {
        shapeFile = new ShapeFile();
        shapeFile.READ();

        stateShapes = new ArrayList<ShpPolygon>();
        stateInfos = new ArrayList<String[]>();
        initStateShapes();
    }

    private void initStateShapes() throws Exception {

        int number_of_shapes;
        if (stateShapes.isEmpty())
        {
            if (shapeFile.getSHP_shapeType() != STATE_SHAPEFILE_TYEPE)
            {
                throw new Exception("Invalid shape file type");
            }

            number_of_shapes = shapeFile.getSHP_shapeCount();

            for (int i = 0; i < number_of_shapes; i++) {
                stateShapes.add(i, (ShpPolygon) shapeFile.getSHP_shape(i));
                stateInfos.add(i, shapeFile.getDBF_record(i));
            }
        }

    }

    public StateInfo getStateInfoForPoint(double pointLatitude, double pointLongitude) {
        return getPointStateInfo(pointLatitude, pointLongitude);
    }

    private StateInfo getPointStateInfo(double pointLatitude, double pointLongitude) {

        StateInfo stateInfo = new StateInfo(shapeFile.getDBF_field());

        for (int i = 0; i < stateShapes.size(); i++) {
            if (pointBelongsToState(pointLatitude, pointLongitude, stateShapes.get(i)))
            {
                stateInfo.hydrateFromDbfRecord(stateInfos.get(i));
                break;
            }
        }
        
        return stateInfo;
    }

    private boolean pointBelongsToState(double pointLatitude, double pointLongitude, ShpPolygon stateShape) {

        double[][] bbox;
        double minLat, maxLat, minLong, maxLong;


        bbox = stateShape.getBoundingBox();
        minLat = bbox[0][0];
        maxLat = bbox[0][1];
        minLong = bbox[1][0];
        maxLong = bbox[1][1];


        /*
         * State boundary box check
         */
        if (pointLatitude < minLat || pointLatitude > maxLat ||
            pointLongitude < minLong || pointLongitude > maxLong)
        {
            return false;
        }

        return pointIsWithinStateBoundaries(pointLatitude, pointLongitude, stateShape);
    }

    /**
     * State boundaries check
     *
     * @param pointLatitude Latitude of point
     * @param pointLongitude Longitude of point
     * @param stateShape State shape data
     * @return
     */
    private boolean pointIsWithinStateBoundaries(double pointLatitude, double pointLongitude, ShpPolygon stateShape) {
        double[][] vertexes;
        int vertexes_count;
        final int latitude_index = 0;
        final int longitude_index = 1;

        vertexes_count = stateShape.getNumberOfPoints();
        vertexes = stateShape.getPoints();

        int i, j;
        boolean state_boundary_check_result = false;

        for (i = 0, j = vertexes_count-1; i < vertexes_count; j = i++) {
            if ( ((vertexes[i][latitude_index]>pointLatitude) != (vertexes[j][latitude_index]>pointLatitude)) &&
                    (pointLongitude < (vertexes[j][longitude_index]-vertexes[i][longitude_index]) * (pointLatitude-vertexes[i][latitude_index]) / (vertexes[j][latitude_index]-vertexes[i][latitude_index]) + vertexes[i][longitude_index]) )
                state_boundary_check_result = !state_boundary_check_result;
        }
        return state_boundary_check_result;
    }
}
