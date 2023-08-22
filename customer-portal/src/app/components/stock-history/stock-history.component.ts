import { Component } from '@angular/core';
import Chart from 'chart.js/auto';
import { StockHistory } from 'src/app/model/StockHistory';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-stock-history',
  templateUrl: './stock-history.component.html',
  styleUrls: ['./stock-history.component.css']
})
export class StockHistoryComponent {
  public chart: any;
  stockHistoryData!: StockHistory [];


  ngOnInit(): void {
    this.getStockHistory();

    // setInterval(() => {
    //   this.chart.destroy();
    //   this.get();
    // },1000)
  }


  constructor(private httpClient:HttpClient) { }
  
  getStockHistory() {
    const url = `http://localhost:8091/api/stockDetails/getStockHistory/1`;

      this.httpClient.get(url).subscribe((res: any) => {
      this.stockHistoryData = res.data
      console.log('networhdata', this.stockHistoryData)
      this.createChart();
    })
    
  }

  createChart(){

    const labels = this.stockHistoryData.map(data => data.time);
    const dataValues = this.stockHistoryData.map(data => data.stockPrice)
    
    this.chart = new Chart("MyChart", {
      type: 'line', //this denotes tha type of chart

      data: {// values on X-Axis
        labels :labels ,
	       datasets: [
          {
            label: "StockName",
            data: dataValues,
            backgroundColor: 'blue'
          },
        ]
      },
      options: {
        aspectRatio:2.5
      }
      
      });
    
  }
}
