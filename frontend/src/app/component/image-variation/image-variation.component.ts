import { Component, OnInit } from '@angular/core';
import {Image, ImageData} from "../../model/image";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {OpenAiService} from "../../service/open-ai.service";

@Component({
  selector: 'app-image-variation',
  templateUrl: './image-variation.component.html',
  styleUrls: ['./image-variation.component.css']
})
export class ImageVariationComponent implements OnInit {

  form!: FormGroup;
  isLoading = false;
  imageList: ImageData[] = [];

  constructor(private fb: FormBuilder, private imageService: OpenAiService) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      image: [null, Validators.required],
      numberOfImages: [1, [Validators.required, Validators.min(1)]],
      imageSize: ['256x256', Validators.required]
    });
  }

  onFileChange(event: any): void {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      this.form.patchValue({ image: file });
    }
  }

  onSubmit(): void {
    if (this.form.invalid) {
      return;
    }

    this.isLoading = true;
    const formData = new FormData();
    // @ts-ignore
    formData.append('image', this.form.get('image').value);
    // @ts-ignore

    formData.append('n', this.form.get('numberOfImages').value);
    // @ts-ignore

    formData.append('size', this.form.get('imageSize').value);

    this.imageService.createImages(formData).subscribe(
      (response: Image) => {
        this.imageList = response.data;
        this.isLoading = false;
      },
      (error) => {
        console.error(error);
        this.isLoading = false;
      }
    );
  }

}
