import { Component } from '@angular/core';
import Chart from 'chart.js/auto';
import { NetworthService } from 'src/app/services/networth.service';
import { NetWorth } from 'src/app/model/NetWorth';

@Component({
  selector: 'app-line-chart',
  templateUrl: './line-chart.component.html',
  styleUrls: ['./line-chart.component.css']
})
export class LineChartComponent {

  public chart: any;
  netWorthData!: NetWorth [];

  ngOnInit(): void {
    this.get();

    setInterval(() => {
      this.chart.destroy();
      this.get();
    },2000)
  }


  constructor(private netWorthService: NetworthService) { }
  
  get() {
      this.netWorthService.getNetWorth().subscribe(res => {
      this.netWorthData = res.data
      console.log('networhdata', this.netWorthData)
      this.createChart();
    })
    
  }

  createChart(){

    const labels = this.netWorthData.map(data => data.time);
    const dataValues = this.netWorthData.map(data => data.netWorthValue)
    
    this.chart = new Chart("MyChart", {
      type: 'line', //this denotes tha type of chart

      data: {// values on X-Axis
        labels :labels ,
	       datasets: [
          {
            label: "NetWorth",
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
