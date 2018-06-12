import org.gdal.gdal.gdal;
import org.gdal.ogr.DataSource;
import org.gdal.ogr.Feature;
import org.gdal.ogr.FeatureDefn;
import org.gdal.ogr.FieldDefn;
import org.gdal.ogr.Geometry;
import org.gdal.ogr.Layer;
import org.gdal.ogr.ogr;

public class ShapeFileWrite {

    public static void main(String[] args) {


        String strVectorFile ="weather/tmd5/temp2/test";

        // Register all drive
        ogr.RegisterAll();

        // In order to support Chinese path, please add the following code
        gdal.SetConfigOption("GDAL_FILENAME_IS_UTF8","NO");
        // In order to make the attribute table field support Chinese, please add the following sentence
        gdal.SetConfigOption("SHAPE_ENCODING","");

        //Create a data, here to create the ESRI SHP file as an example
        String strDriverName = "ESRI Shapefile";
        org.gdal.ogr.Driver oDriver =ogr.GetDriverByName(strDriverName);
        if (oDriver == null)
        {
            System.out.println(strVectorFile+ " The drive is not available!\n");
            return;
        }

        // Create a data source
        DataSource oDS = oDriver.CreateDataSource(strVectorFile,null);
        if (oDS == null)
        {
            System.out.println("Create a vector file ["+ strVectorFile +"] failure!\n" );
            return;
        }

        // Create layer, creating a polygon layer, there is no specified spatial reference, if necessary, need to be specified here.
        Layer oLayer =oDS.CreateLayer("TestPolygon", null, ogr.wkbPolygon, null);
        if (oLayer == null)
        {
            System.out.println("Layer creation failed!\n");
            return;
        }

        // Create an attribute table
        // First create a call FieldID integer attribute
        FieldDefn oFieldID = new FieldDefn("FieldID", ogr.OFTInteger);
        oLayer.CreateField(oFieldID, 1);

        // Create a FeatureName named character attributes, characters of length 50
        FieldDefn oFieldName = new FieldDefn("FieldName", ogr.OFTString);
        oFieldName.SetWidth(100);
        oLayer.CreateField(oFieldName, 1);

        FeatureDefn oDefn =oLayer.GetLayerDefn();

        // Create triangular elements
        Feature oFeatureTriangle = new Feature(oDefn);
        oFeatureTriangle.SetField(0, 0);
        oFeatureTriangle.SetField(1, "Triangle");
        Geometry geomTriangle =Geometry.CreateFromWkt("POLYGON ((0 0,20 0,10 15,0 0))");
        oFeatureTriangle.SetGeometry(geomTriangle);

        oLayer.CreateFeature(oFeatureTriangle);

        // Create a rectangular elements
        Feature oFeatureRectangle = new Feature(oDefn);
        oFeatureRectangle.SetField(0, 1);
        oFeatureRectangle.SetField(1, "Rectangular");
        Geometry geomRectangle =Geometry.CreateFromWkt("POLYGON ((30 0,60 0,60 30,30 30,30 0))");
        oFeatureRectangle.SetGeometry(geomRectangle);

        oLayer.CreateFeature(oFeatureRectangle);

        // Create five angle shaped elements
        Feature oFeaturePentagon = new Feature(oDefn);
        oFeaturePentagon.SetField(0, 2);
        oFeaturePentagon.SetField(1, "Five angle");
        Geometry geomPentagon =Geometry.CreateFromWkt("POLYGON ((70 0,85 0,90 15,80 30,65 15,70 0))");
        oFeaturePentagon.SetGeometry(geomPentagon);

        oLayer.CreateFeature(oFeaturePentagon);

        //Write to file
        oLayer.SyncToDisk();
        oDS.SyncToDisk();

        System.out.println("\nThe N data set created!\n");
    }
}