import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { AllHoldAssetsComponent } from './components/all-hold-assets/all-hold-assets.component';
import { PieChartComponent } from './components/pie-chart/pie-chart.component';
import { LineChartComponent } from './components/line-chart/line-chart.component';

const routes: Routes = [
  {
    path:'home',
    title: 'homepage',
    component:HomeComponent,
    children: [
      {
        path:'all-hold-assets',
        component:AllHoldAssetsComponent,
      },
      {
        path:'pie-chart',
        component:PieChartComponent,
      },
      {
        path:'line-chart',
        component:LineChartComponent,
      }
    ]
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
