package algorithms.kd;

import lombok.Getter;

/**
 * @author lichen
 * @date 2022-5-12 18:42
 */
public class Point {
    @Getter
    private final double[] coordinates;
    private final String string;
    private static final String SEP = ",";

    public boolean equalsWithKey(Point p) {
        return equalsWithKey(p.coordinates);
    }

    public boolean equalsWithKey(double[] keyVectorParam) {
        if (keyVectorParam == null || keyVectorParam.length != coordinates.length) {
            return false;
        }
        for (int i = 0; i < keyVectorParam.length; i++) {
            if (keyVectorParam[i] != coordinates[i]) {
                return false;
            }
        }
        return true;
    }

    public Point(double[] coordinates) {
        this.coordinates = coordinates;
        StringBuilder sb = new StringBuilder();
        for (double value : coordinates) {
            sb.append(value).append(",");
        }
        String localString = sb.toString();
        string = localString.substring(0, localString.length() - SEP.length());
    }

    public double getInDimension(int k) {
        return coordinates[k];
    }

    public int getDimensions() {
        return coordinates.length;
    }

    @Override
    public String toString() {
        return string;
    }
}
