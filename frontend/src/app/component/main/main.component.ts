import { Component, OnInit } from '@angular/core';
import {OpenAiService} from "../../service/open-ai.service";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  openAiForm!: FormGroup;
  response!: string;
  loading: boolean = false;

  constructor(private formBuilder: FormBuilder, private openAiService: OpenAiService) {}

  ngOnInit() {
    this.openAiForm = this.formBuilder.group({
      message: '',
      temperature: 0
    });
  }

  onSubmit() {
    const message = this.openAiForm.value.message;
    const temperature = this.openAiForm.value.temperature;
    this.loading = true;
    this.openAiService.getOpenAiResponse(message, temperature)
      .subscribe(response => {
        this.response = response.response;
        this.loading = false;
      });
  }


}
