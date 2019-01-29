package alexpr.co.uk.twkotlin.utils;

public class ParseUrlUtil {

    /**
    Remove all the special characters and replace spaces with '-'
     */
    public static String parseUrlForItem(String item) {
        return item.toLowerCase().replaceAll("[^A-Z a-z]","").replaceAll(" ", "-");
    }
}
