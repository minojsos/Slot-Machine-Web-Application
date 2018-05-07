interface Isymbol{

    setName(name:string):void;
    
    getName():string;
    
    setValue(value:number):void;
    
    getValue():number;   
    
}

class Symbols implements Isymbol{
    private name: string;
    private value:number;
    
    constructor(name:string,value:number){
        this.name=name;
        this.value=value;

    }
    public setName(name:string){
        this.name=name;
    }
    public getName():string{
        return this.name;
    }
    public setValue(value:number){
        this.value=value;
    }
    public getValue():number{
        return this.value;
    }
}

function symbolObjects(jsonObj):Symbols {

    var obj:Symbols = new Symbols(jsonObj.name,jsonObj.value);

    return obj;

}