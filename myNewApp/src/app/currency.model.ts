export class Currency {
    
    id:number
    name:String
    ticker:String
    number_of_coins:String
    market_cap:String

    constructor(obj?:any){
        this.id = obj.id;
        this.name = obj.name;
        this.ticker = obj.ticker;
        this.number_of_coins =obj.number_of_coins;
        this.market_cap =obj.market_cap;
    }
}
