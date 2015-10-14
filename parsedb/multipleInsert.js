 Parse.initialize("oqyzHQVvY1Zz2oEmEpRbvRneyEo5QrNJuUr0hIFL", "5MAfxOIM9t2mZrfs7Lk0qE0vRsylC9g6ezwHCzXJ");
    
    var promisesPhase1 = [],
        promisesPhase2 = [];

    var Employee = Parse.Object.extend({className: "Employee"});
    var EmployeeCollection = Parse.Collection.extend({model: Employee});
    var employees = new EmployeeCollection(
        [
            {"firstName": "James", "lastName": "King", "managerName": "", "title": "President and CEO", "department": "Corporate", "cellPhone": "617-000-0001", "officePhone": "781-000-0001", "email": "jking@fakemail.com", "city": "Boston, MA", "pic": "james_king.jpg"},
            {"firstName": "Steven", "lastName": "Wells", "managerName": "John Williams", "title": "Software Architect", "department": "Engineering", "cellPhone": "617-000-0012", "officePhone": "781-000-0012", "email": "swells@fakemail.com", "city": "Boston, MA", "pic": "steven_wells.jpg"}
        ]
    );

    // Pass 1: Save basic employee information
    employees.each(function(employee) {
        promisesPhase1.push(employee.save());
    });

    // Pass 2: Set manager information and save
    Parse.Promise.when(promisesPhase1).then(function() {
        employees.at(0).set("managerId", null);
        employees.at(1).set("managerId", employees.at(0).id);
        employees.each(function(employee) {
            promisesPhase2.push(employee.save());
        });
    });
    Parse.Promise.when(promisesPhase2).then(function() {
        alert("Database populated");
    });