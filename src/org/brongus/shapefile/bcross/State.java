package org.brongus.shapefile.bcross;

import org.brongus.shapefile.ShapeFile;
import org.brongus.shapefile.files.shp.shapeTypes.ShpShape;

import java.util.ArrayList;

/**
 * Created by lazar on 3/22/14.
 */
public class State {

    private static final ShpShape.Type STATE_SHAPEFILE_TYEPE = ShpShape.Type.Polygon;

    private ShapeFile shapeFile;

    private ArrayList<ShpShape> stateShapes;
    private ArrayList<String[]> stateInfos;


    public State() throws Exception {
        shapeFile = new ShapeFile();
        shapeFile.READ();

        stateShapes = new ArrayList<ShpShape>();
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
                stateShapes.add(i, (ShpShape) shapeFile.getSHP_shape(i));
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

    private boolean pointBelongsToState(double pointLatitude, double pointLongitude, ShpShape stateShape) {
        return true;
    }
}
