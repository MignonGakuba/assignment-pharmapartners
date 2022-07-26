export class Currency {
    
    Id:number
    Name:String
    TICKER:String
    NUMBER_OF_COINS:String
    MARKET_CAP:String

    constructor(obj?:any){
        this.Id = obj.id;
        this.Name = obj.name;
        this.TICKER = obj.ticker;
        this.NUMBER_OF_COINS =obj.number_OF_COINS;
        this.MARKET_CAP =obj.market_CAP;
    }
}
