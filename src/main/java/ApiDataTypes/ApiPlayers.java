package ApiDataTypes;

import com.google.gson.annotations.SerializedName;

public class ApiPlayers {
    /*
    "players": {
        "max": 20,
        "online": 1,
        "sample": [
          {
            "id": "487db537-1253-41cf-9a19-73006ef31e3d",
            "name": "cliffhanger13"
          }
        ]
      },
     */

    public int getMax() {
        return max;
    }

    public int getOnline() {
        return online;
    }

    public ApiPlayerInfo[] getSample() {
        return sample;
    }

    @SerializedName("max")
    private int max;

    @SerializedName("online")
    private int online;

    @SerializedName("sample")
    private ApiPlayerInfo[] sample;

}
