import { Movement } from './Movement';
import * as moment from 'moment';

export class Invoice {
    id: number;
    totalSum: number;
    paid: boolean;
    date: Date;

    movements: Movement[];

    constructor(_id: number, _totalSum: number, _paid: boolean, _date: Date) {
        this.id = _id;
        this.totalSum = _totalSum;
        this.paid = _paid;
        this.date = _date;
    }

    dateToString(): string {
        return moment(this.date).format('YYYY-MM');
    }

    totalSumToString(): string {
        let temp = '€' + this.totalSum;
        if (temp.indexOf('.') < 0) {
            temp = temp + ',-';
        } else {
            temp = temp.replace('.', ',');
            if (temp.substr(temp.indexOf(',') + 1).length < 2) {
                temp = temp + '0';
            }
        }
        return temp;
    }
}
