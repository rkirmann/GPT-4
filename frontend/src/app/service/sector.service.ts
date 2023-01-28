import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Sector} from "../model/sector.model";
import {UserSectors} from "../model/user-sectors.model";

@Injectable({
  providedIn: 'root'
})
export class SectorService {

  constructor(private http: HttpClient) { }

  public getSectors(): Observable<Sector[]> {
    return this.http.get<Sector[]>("/api/sectors");
  }

  save(value: UserSectors): Observable<UserSectors> {
    return this.http.post<UserSectors>("/api/sectors", value);
  }


}
