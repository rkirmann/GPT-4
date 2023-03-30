import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {UiMessagesComponent} from "./component/ui-messages/ui-messages.component";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MainComponent } from './component/main/main.component';
import { HeaderComponent } from './component/header/header.component';
import { ImageComponent } from './component/image/image.component';
import { ImageVariationComponent } from './component/image-variation/image-variation.component';

@NgModule({
  declarations: [
    AppComponent,
    UiMessagesComponent,
    MainComponent,
    HeaderComponent,
    ImageComponent,
    ImageVariationComponent,
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        ReactiveFormsModule,
        BrowserAnimationsModule,
        FormsModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
