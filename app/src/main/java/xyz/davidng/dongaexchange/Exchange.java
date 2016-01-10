package xyz.davidng.dongaexchange;

/**
 * Created by admin on 1/10/2016.
 */
public class Exchange {
    String type;
    String image;
    String muatienmat;
    String bantienmat;

    public Exchange(String type, String image, String muatienmat, String bantienmat) {
        this.type = type;
        this.image = image;
        this.muatienmat = muatienmat;
        this.bantienmat = bantienmat;
    }

    public String getType() {
        return type;
    }

    public String getImageurl() {
        return image;
    }

    public String getMuatienmat() {
        return muatienmat;
    }

    public String getBantienmat() {
        return bantienmat;
    }
}
