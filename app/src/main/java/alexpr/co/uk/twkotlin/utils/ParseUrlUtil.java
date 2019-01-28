package alexpr.co.uk.twkotlin.utils;

public class ParseUrlUtil {

    /**
    Remove all the special characters and replace spaces with '-'
     */
    public static String parseUrlForItem(String item) {
        String result = item.replaceAll("[^A-Z a-z]","");
        result = result.replaceAll(" ", "-");
        return result;
    }
}
