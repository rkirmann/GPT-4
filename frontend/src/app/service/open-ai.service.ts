import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Image} from "../model/image";

@Injectable({
  providedIn: 'root'
})
export class OpenAiService {

  constructor(private http: HttpClient) { }

  public getOpenAiResponse(message: string, temperature: number): Observable<{ response: string }> {
    return this.http.post<{ response: string }>('/api/chat', {message: message, temperature: temperature});
  }

  public getImageResponse(message: string, size: string, amount: number): Observable<Image> {
    return this.http.post<Image>('/api/image', {message: message, size: size, amount: amount});
  }
  createImages(formData: FormData): Observable<Image> {
    return this.http.post<Image>('api/image-variations', formData);
  }
}
