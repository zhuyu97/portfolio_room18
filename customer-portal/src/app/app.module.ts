import { HttpClient } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { PieChartComponent } from './components/pie-chart/pie-chart.component';
import { ChartModule } from 'primeng/chart'
import { HttpClientModule } from '@angular/common/http';
import { AllHoldAssetsComponent } from './components/all-hold-assets/all-hold-assets.component';
import { AllholdassetsService } from './services/allholdassets.service';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatTableModule} from '@angular/material/table';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    PieChartComponent,
    AllHoldAssetsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ChartModule,
    HttpClientModule,
    MatFormFieldModule,
    MatTableModule,
  ],
  providers: [AllholdassetsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
