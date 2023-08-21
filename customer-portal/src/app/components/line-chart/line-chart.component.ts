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

    // setInterval(() => {
    //   this.chart.destroy();
    //   this.get();
    // },1000)
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
    
    this.chart = new Chart("MyChart", {
      type: 'line', //this denotes tha type of chart

      data: {// values on X-Axis
        labels: [ this.netWorthData[0].time, this.netWorthData[1].time, this.netWorthData[2].time,this.netWorthData[3].time,
        this.netWorthData[4].time, this.netWorthData[5].time, this.netWorthData[6].time,this.netWorthData[7].time,this.netWorthData[8].time,
        this.netWorthData[9].time, this.netWorthData[10].time, this.netWorthData[11].time,this.netWorthData[12].time,this.netWorthData[13].time,
        this.netWorthData[14].time,this.netWorthData[15].time,this.netWorthData[16].time,this.netWorthData[17].time,this.netWorthData[18].time,], 
	       datasets: [
          {
            label: "NetWorth",
            data: [this.netWorthData[0].netWorthValue, this.netWorthData[1].netWorthValue, this.netWorthData[2].netWorthValue,this.netWorthData[3].netWorthValue,
            this.netWorthData[4].netWorthValue, this.netWorthData[5].netWorthValue, this.netWorthData[6].netWorthValue,this.netWorthData[7].netWorthValue,this.netWorthData[8].netWorthValue,
            this.netWorthData[9].netWorthValue, this.netWorthData[10].netWorthValue, this.netWorthData[11].netWorthValue,this.netWorthData[12].netWorthValue,this.netWorthData[13].netWorthValue,
            this.netWorthData[14].netWorthValue,this.netWorthData[15].netWorthValue,this.netWorthData[16].netWorthValue,this.netWorthData[17].netWorthValue,this.netWorthData[18].netWorthValue,],
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
