import { Component } from '@angular/core';
import { AssetsValue } from 'src/app/model/getAllAssetsValue';
import {AllassetsvalueService} from 'src/app/services/allassetsvalue.service';

@Component({
  selector: 'app-pie-chart',
  templateUrl: './pie-chart.component.html',
  styleUrls: ['./pie-chart.component.css'],
})
export class PieChartComponent {
  alldata: any;

  constructor(private dataService: AllassetsvalueService) { }

  getData() {
    const assetsMap: Map<string, number> = new Map<string, number>();
    this.dataService.getAllAssetsValue().subscribe(response => {
        response.map(item => {
            assetsMap.set(item.name, item.num);
          }
        );
    });
    console.log(assetsMap);
    return assetsMap;
  }
  ngOnInit() {
    
    console.log(this.data);
    console.log(this.getData())
  }
  
  title = 'GFG';
  data = {
      labels: ['A', 'B', 'C'],
      datasets: [
          {
              data: [10,20,30],
              backgroundColor: [
                  "#42A5F5",
                  "#66BB6A",
                  "#FFA726"
              ],

          }
      ]
  };

  chartOptions = {
      plugins: {
          legend: {
              labels: {
                  color: '#495057'
              }
          }
      }
  }

  onDeactivate(data: any): void {
    console.log('Deactivate', JSON.parse(JSON.stringify(data)));
  }
}
