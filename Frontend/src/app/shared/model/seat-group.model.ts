import {Serializable, Serialize, SerializeProperty} from 'ts-serializer';

@Serialize({})
export class SeatGroup extends Serializable {
  @SerializeProperty({
    map: 'id'
  })
  private _id: number;

  @SerializeProperty({
    map: 'rowsNum',
    optional: true
  })
  private _rowsNum: number;

  @SerializeProperty({
    map: 'colsNum',
    optional: true
  })
  private _colsNum: number;

  @SerializeProperty({
    map: 'parterre'
  })
  private _parterre: boolean;

  @SerializeProperty({
    map: 'xCoordinate'
  })
  private _xCoordinate: number;

  @SerializeProperty({
    map: 'yCoordinate'
  })
  private _yCoordinate: number;

  @SerializeProperty({
    map: 'totalSeats',
    optional: true
  })
  private _totalSeats: number;

  @SerializeProperty({
    map: 'name'
  })
  private _name: string;

  @SerializeProperty({
    map: 'angle'
  })
  private _angle: number;

  private _changed = false;

  get changed(): boolean {
    return this._changed;
  }

  set changed(value: boolean) {
    this._changed = value;
  }

  get angle(): number {
    return this._angle;
  }

  set angle(value: number) {
    this._angle = value;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get rowsNum(): number {
    return this._rowsNum;
  }

  set rowsNum(value: number) {
    this._rowsNum = value;
  }

  get colsNum(): number {
    return this._colsNum;
  }

  set colsNum(value: number) {
    this._colsNum = value;
  }

  get parterre(): boolean {
    return this._parterre;
  }

  set parterre(value: boolean) {
    this._parterre = value;
  }

  get xCoordinate(): number {
    return this._xCoordinate;
  }

  set xCoordinate(value: number) {
    this._xCoordinate = value;
  }

  get yCoordinate(): number {
    return this._yCoordinate;
  }

  set yCoordinate(value: number) {
    this._yCoordinate = value;
  }

  get totalSeats(): number {
    return this._totalSeats;
  }

  set totalSeats(value: number) {
    this._totalSeats = value;
  }

  get name(): string {
    return this._name;
  }

  set name(value: string) {
    this._name = value;
  }
}
