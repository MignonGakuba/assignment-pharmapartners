import { Component } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Currency } from './currency.model';
import { JsonResult } from './json-result.model';
import { BackendAPIService } from './services/backend-api.service';

const COLUMNS_SCHEMA = [
  {
    key: 'ticker',
    type: 'text',
    label: 'TICKER',
  },
  {
    key: 'name',
    type: 'text',
    label: 'NAME',
  },
  {
    key: 'number_of_coins',
    type: 'text',
    label: 'NUMBER_OF_COINS',
  },
  {
    key: 'market_cap',
    type: 'text',
    label: 'MARKET_CAP',
  },
  {
    key: 'isEdit',
    type: 'isEdit',
    label: '',
  },
];
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})



export class AppComponent {

  title = 'Assignment Currencies WebApp';
  
  constructor(
    private currencyService: BackendAPIService)
    {}

    displayedColumns: string[] = COLUMNS_SCHEMA.map((col) => col.key);
    columnsSchema: any = COLUMNS_SCHEMA;
    dataSource =  new MatTableDataSource<Currency>();
    
    ngOnInit() {
      this.getAllCurreniesInformation();
    }

    getAllCurreniesInformation()
    {
      this.currencyService.getAllCurrency().subscribe((data:JsonResult) => {
     if(data.result) {
          
       // convert the ist of item to right class 
      let retreviedCurrencies: Object[] =  data.item as Object [];
      retreviedCurrencies.map(value =>{
      let currency : Currency = new Currency(value);
      this.dataSource.data.unshift(currency);
      });
         console.log(data.message);
       } else {
         console.log(data.message);
       }
     },
     error => {
       console.log(error, 'error');
     });
    }


  //added new currency 
  addRow() {
    const newCurrency = {
      id: 0 ,
      name: '',
      ticker: '',
      number_of_coins: '',
      market_cap: '',
    };
    this.dataSource.data = [newCurrency, ...this.dataSource.data];

  }

  confirmCurrency(element:Currency){
    this.currencyService.createCurrency(JSON.stringify(element)).subscribe((data:JsonResult) => {

      if(data.result) {
          console.log(data.message);
        } else {
          console.log(data.message);
        }
      },
      error => {
        console.log(error, 'error');
      });
  }


  removeRow(id: number) {
     this.dataSource.data = this.dataSource.data.filter((u) => u.id !== id);  
     let currency = this.dataSource.data.filter((u) => u.id = id);  


     this.currencyService.deleteCurrency(JSON.stringify(currency)).subscribe((data:JsonResult) =>{
      if(data.result) {   
        console.log(data.message);
        } else {
          console.log(data.message);
        }
      },
      error => {
        console.log(error, 'error');
      });

  }
    

  confirmToUpdate(id: number) {

    this.dataSource.data = this.dataSource.data.filter((u) => u.id !== id);  
    let currency = this.dataSource.data.filter((u) => u.id = id);  

    this.currencyService.updateCurrency(JSON.stringify(currency)).subscribe((data:JsonResult) =>{
     if(data.result) {   
       console.log(data.message);
       } else {
         console.log(data.message);
       }
     },
     error => {
       console.log(error, 'error');
     });

    }
  
}
  function addRow() {
    throw new Error('Function not implemented.');
  }

