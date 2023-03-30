import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class OpenAiService {

  constructor(private http: HttpClient) { }

  public getOpenAiResponse(message: string, temperature: number): Observable<{ response: string }> {
    return this.http.post<{ response: string }>('/api/chat', {message: message, temperature: temperature});
  }
}
