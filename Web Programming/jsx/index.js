const express = require('express');
const cors = require('cors');
const {v4:uuidv4} = require('uuid');
const sqlite3 = require('sqlite3').verbose();
const dbSource = "todo.db"
const db = new sqlite3.Database(dbSource);

const HTTP_PORT = 8000;
console.log("Listening on port " + HTTP_PORT);
var app = express();
app.use(cors());
 
class Task {
    constructor(strName,strDate, strLocation, strInstruction){
        this.name = strName;
        this.date = strDate;
        this.location = strLocation;
        this.instructions = strInstruction;
        this.staus = strStatus;
        this.taskID = uuidv4();
    }
}
 
var arrTasks = [];
app.get("/", (req,res,next) => {
    res.status(200).send(arrTasks);
})
 
app.post("/task",(req,res,next) => {
    let strName = req.query.name;
    let strDate = req.query.date;
    let strLocation = req.query.location;
    let strInstruction = req.query.instructions;
    let strStatus = req.query.status;
    let strCommand = "INSERT INTO tblTasks VALUES(?,?,?,?,?,?)";
    if(strName && strDate && strLocation && strInstruction && strStatus){
        let objTask = new Task(strName,strDate,strLocation,strInstruction,strStatus);
        let arrParameters = [strName,strDate,strLocation,strInstruction,strStatus,objTask.taskID];
        db.run(strCommand,arrParameters,function (err,result){
            if(err){
                res.status(400).json({error:err.message})
            }
            else{
                res.status(201).json({
                    message:"success",
                    
                })
            }
        })
    }
    else{
        res.status(400).json({error:"Not all parameters provided"})
    }
})
 
app.get("/task",(req,res,next) => {
    let strName = req.params.name;
    if(strName){
        let strCommand = "SELECT * FROM tblTask WHERE name = ?";
        let arrParameters = [strName];
        db.all(strCommand,arrParameters,(err,row) => {
            if(err){
                res.status(400).json({error:err.message})
            }
            else{
                if(row.length < 1){
                    res.status(200).json({message:"error: not found"})
                }
                else{
                    res.status(200).json({message:"Success",task:row})
                }
            }
        })
    }    
    else {
        res.status(400).json({error:"No task name provided"});
    }
})

 
app.delete("/task",(req,res,next) => {
    let strName = req.query.name;
    if(strName){
        arrTask.forEach(function(task,index){
            if(task.name == strName){
                arrTask.splice(index,-1);
                res.status(200).send(task);
            }
        })
        res.status(200).send({message:'Task Not Found'});
    } else {
        arrTask = [];
        res.status(200).send(arrTask);
    }
})
 

 
app.listen(HTTP_PORT);