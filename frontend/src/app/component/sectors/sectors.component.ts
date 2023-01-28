import {Component, Input, OnInit} from '@angular/core';
import {AbstractControl, FormArray, FormControl, FormGroup, ValidatorFn, Validators} from "@angular/forms";
import {Sector} from "../../model/sector.model";
import {SectorService} from "../../service/sector.service";
import {UiMessageService} from "../../service/ui-message.service";
import {UserSectors} from "../../model/user-sectors.model";
import {delay} from "rxjs";

@Component({
  selector: 'app-sectors',
  templateUrl: './sectors.component.html',
  styleUrls: ['./sectors.component.css']
})
export class SectorsComponent implements OnInit {
  sectors: Sector[] = [];
  sectorsForm = new FormArray([], Validators.required);
  form = new FormGroup({
    name: new FormControl('', Validators.required),
    sectors: this.sectorsForm,
    checkbox: new FormControl('', Validators.required)
  });


  constructor(private sectorService: SectorService, private uiMessageService: UiMessageService) {
    sectorService.getSectors().subscribe(  {
      next: result => {
        this.sectors = result;
        },
      error: err => {this.uiMessageService.error("Error loading sectors!")}
    });
  }

  ngOnInit(): void {
  }

  addOrRemoveSectorsForm(id: number, $event: Event) {
    // @ts-ignore
    console.log($event.target['checked']);
    console.log(this.sectorsForm.value);
    // @ts-ignore
    if ($event.target['checked']) {
      this.sectorsForm.push(new FormControl(id));
    } else {
      this.sectorsForm.removeAt(this.sectorsForm.value.indexOf(id));
    }
  }

  onSubmit() {
    console.log(this.form);
    console.log(this.form.value);
    if (!this.form.valid) {
      this.form.markAllAsTouched();
      console.error();
      this.uiMessageService.error("Please fill in all required fields");
    } else {
      console.log("submitting");
      this.sectorService.save(this.form.value).subscribe({
        next: (data: UserSectors) => {
          this.uiMessageService.success("Data saved!");
          // here the form resets and data is fetched from the backend
          this.form.reset();
          this.form.patchValue(data);
        },
        error: () => {
          this.uiMessageService.error("Error saving!");
        }
      });
      }
    }




}
