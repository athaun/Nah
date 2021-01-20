package ApiDataTypes;

import com.google.gson.annotations.SerializedName;

public class ApiPlayerInfo {
    /*
    {
      "id": "487db537-1253-41cf-9a19-73006ef31e3d",
      "name": "cliffhanger13"
    }
    */
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

}
