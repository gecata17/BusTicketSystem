import { Trip } from "./trip-model"


export class Town{

    title: string;
    longitude: number;
    latitude:number;
    startTownTrip:Trip[];
    endTownTrip:Trip[];

    constructor(

        title: string,
        longitude: number,
        latitude:number,
        startTownTrip:Trip[],
        endTownTrip:Trip[],

    )
    {
        this.title=title;
        this.longitude=longitude;
        this.latitude=latitude;
        this.startTownTrip=startTownTrip;
        this.endTownTrip=endTownTrip;
    }

    
}