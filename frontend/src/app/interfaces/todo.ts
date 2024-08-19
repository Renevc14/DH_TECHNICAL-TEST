export interface TodoModel {
    "id": number;
    "title": string;
    "description": string;
    "startDate": string;
    "status": string;
    "favorite": boolean;
}

export type FilterType = 'TODO' | 'IN_PROGRESS' | 'DONE';