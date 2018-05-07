var Symbols = /** @class */ (function () {
    function Symbols(name, value) {
        this.name = name;
        this.value = value;
    }
    Symbols.prototype.setName = function (name) {
        this.name = name;
    };
    Symbols.prototype.getName = function () {
        return this.name;
    };
    Symbols.prototype.setValue = function (value) {
        this.value = value;
    };
    Symbols.prototype.getValue = function () {
        return this.value;
    };
    return Symbols;
}());
function symbolObjects(jsonObj) {
    var obj = new Symbols(jsonObj.name, jsonObj.value);
    return obj;
}
