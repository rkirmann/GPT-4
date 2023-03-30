import { Component, OnInit } from '@angular/core';
import {Image, ImageData} from "../../model/image";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {OpenAiService} from "../../service/open-ai.service";

@Component({
  selector: 'app-image',
  templateUrl: './image.component.html',
  styleUrls: ['./image.component.css']
})
export class ImageComponent implements OnInit {

  imageForm!: FormGroup;
  imageSizes = ['256x256', '512x512', '1024x1024'];
  images: ImageData[] = [];
  isLoading = false;

  constructor(private imageService: OpenAiService) { }

  ngOnInit(): void {
    this.imageForm = new FormGroup({
      message: new FormControl('', Validators.required),
      size: new FormControl(this.imageSizes[0], Validators.required),
      amount: new FormControl(1, [Validators.required, Validators.min(1)])
    });
  }

  onSubmit() {
    this.isLoading = true;
    this.imageService.getImageResponse(
      this.imageForm.value.message,
      this.imageForm.value.size,
      this.imageForm.value.amount
    ).subscribe((image: Image) => {
      this.images = image.data;
      this.isLoading = false;
    });
  }


}
