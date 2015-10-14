 Parse.initialize("oqyzHQVvY1Zz2oEmEpRbvRneyEo5QrNJuUr0hIFL", "5MAfxOIM9t2mZrfs7Lk0qE0vRsylC9g6ezwHCzXJ");
    
    var Employee = Parse.Object.extend({className: "Employee"});
    var single = new Employee();
    
    single.save({
      firstName: "entry1",
      lastName: "entry2",
      managerName: "entry4",
      title: "President and CEO",
      department: "Corporate",
      cellPhone: "617-000-0001",
      officePhone: "781-000-0001",
      email: "jking@fakemail.com",
      city: "Boston, MA",
      pic: "james_king.jpg"
    }, {
      success: function(single) {
        // The object was saved successfully.
        alert("Entry added!");
      },
      error: function(single, error) {
        // The save failed.
        // error is a Parse.Error with an error code and message.
        alert("Error while adding entry!");
      }
    });
    
