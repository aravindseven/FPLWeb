    var address = new Array();
    var uProfileLocal;
    function Convert_postCode_To_LatLng(uProfile) {
    	uProfileLocal=uProfile;
            var url = "https://maps.googleapis.com/maps/api/geocode/json?address=Singapore " + uProfile.postalCode + "&sensor=false";
            jQuery.getJSON(url, function (json) {
                Create_LatLng(json);
            }); 
            
            
        return address;    
    }


    /*
    * Get the json file from Google Geo
    */
    function Convert_LatLng_To_Address(lat, lng) {
            var url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + lat + "," + lng + "&sensor=false";
            jQuery.getJSON(url, function (json) {
                Create_Address(json);
            });     
    }

    /*
    * Create an address out of the json 
    */
    function Create_Address(json, callback) {
        if (!check_status(json)) // If the json file's status is not ok, then return
            return 0;
        address['country'] = google_getCountry(json);
        address['block'] = google_getBlock(json);
        address['city'] = google_getCity(json);
        address['street'] = google_getStreet(json);
        address['postal_code'] = google_getPostalCode(json);
        address['country_code'] = google_getCountryCode(json);
        address['formatted_address'] = google_getAddress(json);
        
        uProfileLocal.block= address['block'];
        uProfileLocal.street= address['street'];
        uProfileLocal.country= address['country'];
        
        uProfileLocal.scope.$apply();
    }


	/*
    * Create an address out of the json 
    */
    function Create_LatLng(json, callback) {
        if (!check_status(json)) // If the json file's status is not ok, then return
            return 0;

   Convert_LatLng_To_Address(json["results"][0]["geometry"]["location"]["lat"],	json["results"][0]["geometry"]["location"]["lng"]);


    }

    /* 
    * Check if the json data from Google Geo is valid 
    */
    function check_status(json) {
        if (json["status"] == "OK") return true;
        return false;
    }   

    /*
    * Given Google Geocode json, return the value in the specified element of the array
    */

    function google_getCountry(json) {
        return Find_Long_Name_Given_Type("country", json["results"][0]["address_components"], false);
    }
    function google_getProvince(json) {
        return Find_Long_Name_Given_Type("administrative_area_level_1", json["results"][0]["address_components"], true);
    }
    function google_getCity(json) {
        return Find_Long_Name_Given_Type("locality", json["results"][0]["address_components"], false);
    }
    function google_getBlock(json) {
        return Find_Long_Name_Given_Type("street_number", json["results"][0]["address_components"], false);
    }
	function google_getStreet(json) {
        return Find_Long_Name_Given_Type("route", json["results"][0]["address_components"], false);
    }
    function google_getPostalCode(json) {
        return Find_Long_Name_Given_Type("postal_code", json["results"][0]["address_components"], false);
    }
    function google_getCountryCode(json) {
        return Find_Long_Name_Given_Type("country", json["results"][0]["address_components"], true);
    }
    function google_getAddress(json) {
        return json["results"][0]["formatted_address"];
    }   

    /*
    * Searching in Google Geo json, return the long name given the type. 
    * (if short_name is true, return short name)
    */

    function Find_Long_Name_Given_Type(t, a, short_name) {
        var key;
        for (key in a ) {
            if ((a[key]["types"]).indexOf(t) != -1) {
                if (short_name) 
                    return a[key]["short_name"];
                return a[key]["long_name"];
            }
        }
    } 