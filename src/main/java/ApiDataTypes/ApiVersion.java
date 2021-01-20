package ApiDataTypes;

import com.google.gson.annotations.SerializedName;

public class ApiVersion {
    /*
      "version": {
        "name": "Paper 1.16.4",
        "protocol": 754
      }
    */

    public String getName() {
        return name;
    }

    public int getProtocol() {
        return protocol;
    }

    @SerializedName("name")
    private String name;

    @SerializedName("protocol")
    private int protocol;

}
