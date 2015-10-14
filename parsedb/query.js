Parse.initialize("oqyzHQVvY1Zz2oEmEpRbvRneyEo5QrNJuUr0hIFL", "5MAfxOIM9t2mZrfs7Lk0qE0vRsylC9g6ezwHCzXJ");

/////////////////////////////////////
//get based on objectId
/////////////////////////////////////
    var Employee = Parse.Object.extend("Employee");
    var query = new Parse.Query(Employee);

    query.get("4Cd4wyb43d", {

      success: function(element) {
        // The object was retrieved successfully.
        var firstName = element.get("firstName");
        alert("Success! Retrieved: " + firstName)
      },

      error: function(object, error) {
        // The object was not retrieved successfully.
        // error is a Parse.Error with an error code and message.
        alert("Parse error!")
      }
    });

 /////////////////////////////////////
 //get based on attribute
 /////////////////////////////////////

var Employee = Parse.Object.extend("Employee");
var query = new Parse.Query(Employee);
query.equalTo("managerName", "James King");
query.find({
  success: function(results) {
    alert("Successfully retrieved " + results.length + " scores.");

    // Do something with the returned Parse.Object values
    for (var i = 0; i < results.length; i++) {
      var object = results[i];
      alert(object.id + ' - ' + object.get('managerName'));
    }
  },
  error: function(error) {
    alert("Error: " + error.code + " " + error.message);
  }
});