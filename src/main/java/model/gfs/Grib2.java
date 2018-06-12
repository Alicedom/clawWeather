package model.gfs;

import ucar.ma2.Array;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;
import ucar.nc2.dt.GridCoordSystem;
import ucar.nc2.dt.GridDatatype;
import ucar.nc2.dt.grid.GeoGrid;
import ucar.nc2.dt.grid.GridDataset;
import ucar.nc2.grib.grib2.Grib2RecordScanner;
import ucar.nc2.grib.grib2.Grib2SectionDataRepresentation;
import ucar.nc2.util.NamedObject;
import ucar.unidata.io.RandomAccessFile;

import java.io.IOException;
import java.util.List;

public class Grib2 {

    String testfile = "src/test/file/Download/2";

    public static void main(String[] args) {
        try {
            new Grib2().gribPlugin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gribPlugin() throws IOException {

//        NetcdfFile file = ucar.nc2.NetcdfFile.open(testfile);
//        System.out.println("file info= " + file.getDetailInfo());
//        System.out.println("file.getFileTypeId() = " + file.getFileTypeId());
//        System.out.println("file.getVariables().toString() = " + file.getVariables().toString());
//        NetcdfFile file = ucar.nc2.dataset.NetcdfDataset.open(testfile);
//        NetcdfFile file = ucar.nc2.dataset.NetcdfDataset.open(testfile);
//        List<Variable> listvar = file.getVariables();
//        for (Variable var : listvar) {
//
//            System.out.println("var.getDataType().name() = " + var.getDataType().name());
//            System.out.println("var.getDataType().toString() = " + var.getDataType().toString());
//            System.out.println("var.getNameAndDimensions() = " + var.getNameAndDimensions());
//            System.out.println("var.getDataType() = " + var.getDataType());
//            System.out.println("var.getUnitsString() = " + var.getUnitsString());
//            System.out.println("var.getDescription() = " + var.getDescription());
//        }


         GridDataset dataset = ucar.nc2.dt.grid.GridDataset.open(testfile);
        System.out.println("dataset = " + dataset.getDetailInfo());
        RandomAccessFile raf = new RandomAccessFile(testfile, "r");

        Grib2RecordScanner scan = new Grib2RecordScanner(raf);

        while (scan.hasNext()) {
            ucar.nc2.grib.grib2.Grib2Record gr2 = scan.next();
            Grib2SectionDataRepresentation drs = gr2.getDataRepresentationSection();

            float[] data = gr2.readData(raf, drs.getStartingPosition());
            System.out.println(data[0]);
        }
        raf.close();
    }

    public void exam2() throws IOException {
        GridDataset gds = GridDataset.open(testfile);

//        GridDatatype grid = gds.findGridDatatype("myVariableName");
//        GridCoordSystem gcs = grid.getCoordinateSystem();

        double lat = 8.0;
        double lon = 21.0;

        // find the x,y index for a specific lat/lon position
//        int[] xy = gcs.findXYindexFromLatLon(lat, lon, null); // xy[0] = x, xy[1] = y

        GeoGrid grib = gds.findGridByName("tem");

        List<NamedObject> list = grib.getLevels();
        for (NamedObject ob: list) {
            System.out.println("ob.getName() = " + ob.getName());
            System.out.println("ob.getDescription() = " + ob.getDescription());
        }

        // read the data at that lat, lon and the first time and z level (if any)
//        Array data = grid.readDataSlice(0, 0, xy[1], xy[0]); // note order is t, z, y, x

//        double val = data.getDouble(0); // we know its a scalar
//        System.out.printf("Value at %f %f == %f%n", lat, lon, val);
        RandomAccessFile raf = new RandomAccessFile(testfile, "r");
        Grib2RecordScanner scan = new Grib2RecordScanner(raf);
        while (scan.hasNext()) {
            ucar.nc2.grib.grib2.Grib2Record gr2 = scan.next();
            // do stuff
        }
        raf.close();
    }
}
