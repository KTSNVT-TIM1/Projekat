import {Component, Input, OnInit, Output, EventEmitter} from '@angular/core';
import {Page} from '../../shared/model/page.model';
import {MatSnackBar, PageEvent} from '@angular/material';
import {SearchEventsDTO} from '../../shared/model/search-events-dto.model';
import {LocationApiService} from '../../core/location-api.service';
import {EventApiService} from '../../core/event-api.service';
import {Event} from '../../shared/model/event.model';
import {Location} from '../../shared/model/location.model';
import * as moment from 'moment';

@Component({
  selector: 'app-event-preview-list',
  templateUrl: './event-preview-list.component.html',
  styleUrls: ['./event-preview-list.component.scss']
})
export class EventPreviewListComponent implements OnInit {
  private _events: Event[];
  private _page: Page;
  private _eventCategories: string[];
  private _locationsOptions: Location[];
  private _searchParameters: SearchEventsDTO;
  private _activeSearchParameters: SearchEventsDTO;
  @Output() eventsPageChanged = new EventEmitter<Page>();
  @Output() resetPaginator = new EventEmitter<any>();

  constructor(private eventApiService: EventApiService, private locationApiService: LocationApiService,
              private snackBar: MatSnackBar) {
    this._eventCategories = ['Music', 'Sport', 'Fair', 'Movie', 'Performance', 'Competition'];
    this._searchParameters = new SearchEventsDTO('', null, null, '', '');
    this._activeSearchParameters = new SearchEventsDTO('', null, null, '', '');
  }

  get events(): Event[] {
    return this._events;
  }

  set events(value: Event[]) {
    this._events = value;
  }

  get page(): Page {
    return this._page;
  }

  set page(value: Page) {
    this._page = value;
  }

  get eventCategories(): string[] {
    return this._eventCategories;
  }

  set eventCategories(value: string[]) {
    this._eventCategories = value;
  }

  get locationsOptions(): Location[] {
    return this._locationsOptions;
  }

  set locationsOptions(value: Location[]) {
    this._locationsOptions = value;
  }

  get searchParameters(): SearchEventsDTO {
    return this._searchParameters;
  }

  set searchParameters(value: SearchEventsDTO) {
    this._searchParameters = value;
  }

  get activeSearchParameters(): SearchEventsDTO {
    return this._activeSearchParameters;
  }

  set activeSearchParameters(value: SearchEventsDTO) {
    this._activeSearchParameters = value;
  }

  ngOnInit() {
    this.getLocationsOptions();
    this.getEvents(0, 6);
  }

  private getLocationsOptions() {
    this.locationApiService.getLocationsOptions().subscribe({
      next: (result: Location[]) => {
        this._locationsOptions = result;
      },
      error: (message: string) => {
        this.snackBar.open(JSON.parse(JSON.stringify(message)).error, 'Dismiss', {
          duration: 3000
        });
      }
    });
  }

  public getEvents(page: number, size: number) {
    this.eventApiService.getEvents(page, size).subscribe({
      next: (result: Page) => {
        this._page = result;
        this.eventsPageChanged.emit(result);
        this.events = result.content;
      },
      error: (message: string) => {
        this.snackBar.open(JSON.parse(JSON.stringify(message)).error, 'Dismiss', {
          duration: 3000
        });
      }
    });
  }

  public pageChanged(event: PageEvent) {
    this._page.size = event.pageSize;
    this._page.number = event.pageIndex;
    this.searchEvents(this._page.number, this._page.size);
  }

  private onSubmit() {
    Object.assign(this._activeSearchParameters, this._searchParameters);
    this.searchEvents(0, this._page.size);
  }

  private searchEvents(page: number, size: number) {
    const parameters: SearchEventsDTO = new SearchEventsDTO(this._activeSearchParameters.name,
      this._activeSearchParameters.locationID, this._activeSearchParameters.category,
      this._activeSearchParameters.startDate, this._activeSearchParameters.endDate);
    if (parameters.startDate !== '') {
      parameters.startDate = moment(parameters.startDate).format('DD.MM.YYYY. HH:mm');
    }
    if (parameters.endDate !== '') {
      parameters.endDate = moment(parameters.endDate).format('DD.MM.YYYY. HH:mm');
    }
    this.eventApiService.searchEvents(parameters, page, size).subscribe({
      next: (result: Page) => {
        this._page = result;
        this.eventsPageChanged.emit(result);
        this.events = result.content;
      },
      error: (message: string) => {
        this.snackBar.open(JSON.parse(JSON.stringify(message)).error, 'Dismiss', {
          duration: 3000
        });
      }
    });
  }

  private resetForm(form) {
    form.reset();
    this._searchParameters = new SearchEventsDTO('', null, null, '', '');
    Object.assign(this._activeSearchParameters, this._searchParameters);
    this.searchEvents(0, this._page.size);
  }

}
