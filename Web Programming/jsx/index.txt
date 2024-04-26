/*/
You are required to create a backend system using Node.js and SQLite to manage room reservations.
• Your system should support the basic CRUD operations (Create, Read, Delete) for room reservations.
• Additionally, your system should include the ability to retrieve an object with the following properties: name, primary color, and address for the location where the software is installed. You should implement this using a custom class.
• Ensure proper error handling in your code.
• Your code should be well-structured, readable, and commented where necessary.
• The SQLite database is provided for your review. NOTE: The id column should be
populated with a UUID/GUID.
Tasks:
1.
Implement CRUD operations to manage room reservations using Node.js.
• Create RESTful endpoints for the following operations:
o ‘GET/reservations’-Retrieveallreservations.
o ‘DELETE/reservations/:id’-DeleteareservationbyID.
2. Custom Class Implementation:
Implement a custom class named ‘InstallationInfo’ to represent the location where the software is installed.
• The class should have properties: ‘name’, ‘primaryColor’, and ‘address’.
• Create a RESTful endpoint to:
o ‘GET/installinfo–Returnaninstanceofthisclass
o Uses these values for the keys of the object instance: name – Cookeville
Police, primaryColor – blue, address – 1019 Neal Street
Submission:
- Submit your Node.js code files (‘index.js’).
Note:
• You are encouraged to use libraries like Express.js for building RESTful APIs and SQLite3 for interacting with the SQLite database. Other libraries can be used as needed/desired
• Focus on writing clean, e\icient, and well-documented code.
//*/

const express = require('express');
const cors = require('cors');
const sqlite3 = require('sqlite3').verbose();
const dbSource = "node.db";
const db = new sqlite3.Database(dbSource);
const HTTP_PORT = 8000;
console.log("Listening on port " + HTTP_PORT);
var app = express();
app.use(cors());

// represent the location where the software is installed
class InstallationInfo {
  constructor(name, primaryColor, address) {
    this.name = name;
    this.primaryColor = primaryColor;
    this.address = address;
  }
}

// Return an instance of the I nstallationInfo class
app.get('/installinfo', (req, res) => {
  const installInfo = new InstallationInfo('Cookeville Police', 'blue', '1019 Neal Street');
  res.json(installInfo);
});


// retrieve all reservations

app.get("/reservations", (req,res,next) => {
  let strName = req.query.name;
  if(strName){
      let strCommand = "SELECT * FROM tblReservation WHERE name = ?";
      let arrParameters = [strName];
      db.all(strCommand,arrParameters,(err,row) => {
          if(err){
              res.status(400).json({error:err.message});
          } else {
              if(row.length < 1){
                  res.status(200).json({message:"error: not found"})
              } else{
                  res.status(200).json({message:"success",reservation:row})
              }
          }
      })
  } else {
      res.status(400).json({error:"No reservation name provided"});
  }
})


//Deletea reservation by ID.
app.delete('/reservations/:id', (req, res) => {
  db.run('DELETE FROM tblReservation WHERE id = ?', req.params.id, function(error) {
    if (error) {
      res.status(500).json({ error: error.message });
      return;
    }
    res.json({ message: 'Successfully deleted reservation' });
  });
});

// localhost port
app.listen(HTTP_PORT, () => {
  console.log("Listening on port " + HTTP_PORT);
});
