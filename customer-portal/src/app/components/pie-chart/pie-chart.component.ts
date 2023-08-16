import { Component } from '@angular/core';
import { AssetsValue } from 'src/app/model/getAllAssetsValue';
import {AllassetsvalueService} from 'src/app/services/allassetsvalue.service';


@Component({
  selector: 'app-pie-chart',
  templateUrl: './pie-chart.component.html',
  styleUrls: ['./pie-chart.component.css'],
})
export class PieChartComponent {
  listData: Array<number> = [];
  isShowChart = false;
  data = {};
  chartOptions = {};
  title = 'GFG';

  constructor(private dataService: AllassetsvalueService) { }

  ngOnInit() {
    this.getData();
  }

  getData() {
    this.dataService.getAllAssetsValue().subscribe(data => {
      console.log('Response:', data); // 调试输出响应数据
      this.listData = data.data;
      this.isShowChart = true;
      
      this.data = {
          labels: ['Cash', 'Stock', 'Bond'],
          datasets: [
              {
                  data: [this.listData[0], this.listData[1], this.listData[2]],
                  backgroundColor: [
                      "#42A5F5",
                      "#66BB6A",
                      "#FFA726"
                  ],
    
              }
          ]
      };
    
      this.chartOptions = {
          plugins: {
              legend: {
                  labels: {
                      color: '#495057'
                  }
              }
          }
      }
    });
  }
  


  onDeactivate(data: any): void {
    console.log('Deactivate', JSON.parse(JSON.stringify(data)));
  }
}
