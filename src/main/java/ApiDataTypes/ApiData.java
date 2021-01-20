package ApiDataTypes;

import com.google.gson.annotations.SerializedName;

public class ApiData {
    /*
    {
      "description": "\u00a72                  Zombie Paradise\u00a7f\n\u00a7b                SMP, \u00a76Open on 1.16.4",
      "favicon": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAIAAAAlC+aJAAAEmUlEQVR4Xu3X+VMaZxjAcX7tD+10klgRdZq0gtrIstzKWU5FIYAXKiigHBsuOeIRJDZOhaZq8EDrJJ32b+3rLnTwfV40tjNpTHfnM+88owM8X9iRUeDY0mEmNgz2tMqWIZja0BC5N7XGBEUQp7WRAW1EAqkiYv2qlMiW1sCXbkqpbSllOwEfwAcA//sA56bBAh/56QZs6NuNv9BPlvT4w1pcRc1kSQu5SiojQ0O61Mho/LEs2oeRR/oUkcdjSQlrsHU2B1uuY4A5hSjaCdCb3a7TzdOE3huijMpXMRPs2BbemUPnxisNdkAnOyw3zMFjOxEKcHSgT9D6BNVOgK/4j1iyCl/FCHlfGRcvLUsXegg1LJ1YiRw5lTOrwfABN+ID+IB/6dMKsKQU1rSSCP3WnlVD5jT9rKyHPDv6uYYx2NARnJngH1AOChjPaTHOdS0bcG17cgBaKHmykDyZh9Jni7mLEJQ9D27/GScqXK4QlX5bWb8IEYWrE+Gag4j7VNvdLYDblWjrjxhR6V0Ebo+8uAzD1W8OiL4Zh3cpH8AHQHwAH/BRA+xZJQZ9ayTrgWR9LnF8hRvQiWROg9mzEFHlPVN5n2BxAzqZ8u+J/MlKrrHMCrdOblgunIeIQvvOYNUR2ndwJzcEqzbEu2PACCI1T7swK/V2jqkTRGvTkaoXilV9idosUagyGahMQPM74/C956D/hPw7hg/EB/ABEB/wWQf44Pb/cUC45sVEqn4UkD7CZd7Oo0XX9r0Qeki8FoCY2uziLjkgVHYWzoJEs2XLTFlPsG0lBCTqPlzjmVDUJRT1QN8IHwpF3exvu1oDOru/FH2RO/RDhYOZ4ulCtkFQPI6K+no7QU8qZJ9a+PfQ3dfT17Va9WIEyWM/JnHiF4mEortcj3ofwe2vHPnQN2uxgd8kSO50EX+WG6/+HqGw98Haz37s7uUD2IsPuP3iA268+IDbr44B4V9dmNihe0DbM6AiGNT2i5UiaNjU//yNl+CXmczBfPYwADHVwHfqHqJ++oFI9rWIap3N4aGQ/mp+1xXYdXEnNwi8ZTXGVx5ljtyJtwT5OvoCWoRyhzNj00N3ExCnzqeJpl6p3K81mKk9jf/16GhsGHOfAqb3xu53wL3/BPgAPuDjBaBd43WCAlidkz2Yxfe7VecA967W85MG8u6RAlwlJcZRlNm3pY4tCjImB02xp5Dt+Yh7XXYnnrxGHRIT2TaV5jyFMRUoa15milMYgaugxKAAx0vKuS2DTMyQOT5CMjSVo+7EnVeqlwaIzEWZpSgHaARtbI5dwwfwARAfcO8DCgrHS/nEFoGZkYDVmwGejNyToVncIG8bCD93r6vh6q0A2laQY1DDjyU5IcAQk2DQ33VdUmKMDxoSEoQb0ImMRcWG1WFIFx62JClLcoTFDRQ32FNyKyNlyVrn1eBgKLg6x5KlrRmpLS3lTm5ALFlKF/1BH7lGoAh8D9FzT4g0QTF8PY4qKCEyxodN8RHIGHsKn6QZwMgtjIxoNDw0tnINCngC0XPfEt0Q0IlpTQrutyumzgHWpLz1QeGw7fmAD8AHfO4BfwHh7Xb6nTzSswAAAABJRU5ErkJggg==",
      "latency": 345.806,
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
      "version": {
        "name": "Paper 1.16.4",
        "protocol": 754
      }
    }
    */

    public String getDescription() {
        return description;
    }

    public String getFavicon() {
        return favicon;
    }

    public void getFaviconImage () {

    }

    public double getLatency() {
        return latency;
    }

    public ApiPlayers getPlayers() {
        return players;
    }

    public ApiVersion getVersion() {
        return version;
    }

    @SerializedName("description")
    private String description;

    @SerializedName("favicon")
    private String favicon;

    @SerializedName("latency")
    private double latency;

    @SerializedName("players")
    private ApiPlayers players;

    @SerializedName("version")
    private ApiVersion version;
}
