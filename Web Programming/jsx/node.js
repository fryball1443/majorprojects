const express = require('express');
const cors = require('cors');
const sqlite3 = require('sqlite3').verbose();
const dbSource = "todo.db";
const db = new sqlite3.Database(dbSource);
const HTTP_PORT = 8000;
console.log("Listening on port " + HTTP_PORT);
var app = express();
app.use(cors());

class Fruit {
    constructor(strName,strColor){
        this.name = strName;
        this.color = strColor;
    }
}

var arrFruit = [];
let objBanana = new Fruit('banana','yellow');
let objApple = new Fruit('apple','red');
let objGrape = new Fruit('grape','purple');
arrFruit.push(objBanana);
arrFruit.push(objApple);
arrFruit.push(objGrape);
arrFruit.push(new Fruit('kiwi','brown'));
app.get("/", (req,res,next) => {
    res.status(200).send(arrFruit);
})

app.post("/fruit",(req,res,next) => {
    let strName = req.query.name;
    let strColor = req.query.color;
    let strCommand = "INSERT INTO tblFruit VALUES(?,?)";
    if(strName && strColor){
        let arrParameters = [strName,strColor];
        let objFruit = new Fruit(strName, strColor);
        db.run(strCommand,arrParameters, function(err,result){
            if(err){
                res.status(400).json({error:err.message})
            } else {
                res.status(201).json({
                    message:"success",
                    fruit:objFruit
                })
            }
        })
    } else {
        res.status(400).json({error:"Not all parameters provided"})
    }
})

app.get("/fruit",(req,res,next) => {
    let strName = req.query.name;
    if(strName){
        let strCommand = "SELECT * FROM tblFruit WHERE name = ?";
        let arrParameters = [strName];
        db.all(strCommand,arrParameters,(err,row) => {
            if(err){
                res.status(400).json({error:err.message});
            } else {
                if(row.length < 1){
                    res.status(200).json({message:"error: not found"})
                } else{
                    res.status(200).json({message:"success",fruit:row})
                }
            }
        })
    } else {
        res.status(400).json({error:"No fruit name provided"});
    }
})

app.delete("/fruit",(req,res,next) => {
    let strName = req.query.name;
    if(strName){
        arrFruit.forEach(function(fruit,index){
            if(fruit.name == strName){
                arrFruit.splice(index,-1);
                res.status(200).send(fruit);
            }
        })
        res.status(200).send({message:'Fruit Not Found'});
    } else {
        arrFruit = [];
        res.status(200).send(arrFruit);
    }
})

app.get("/hello", (req,res,next) => {
    let strCommand = 'SELECT * FROM tblFruit';
    db.all(strCommand,(err, row) => {
        if(err){
            res.status(400).json({error:err.message});
        } else {
            res.status(200).json({message:"success",fruit:row})
        }
    })
})

app.listen(HTTP_PORT);