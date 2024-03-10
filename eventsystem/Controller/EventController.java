package com.example.eventsystem.Controller;

import com.example.eventsystem.APIResponse.APIResponse;
import com.example.eventsystem.Model.Event;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    /*• Create a new event (ID , description , capacity, startDate , endDate)
• Display all event .
• Update a event
• Delete a event
• Change capacity
• Search for a event by given id
Hint ( use @JsonFormat(pattern="yyyy-MM-dd") and LocalDateTime )*/

    ArrayList<Event> events = new ArrayList<>();
    @PostMapping("/addEvent")
public APIResponse addEvent(@RequestBody Event event){
        events.add(event);
        return new APIResponse("EVENT "+event.getID()+" IS ADDED SUCCESSFULLY");
}//{"ID":"4321","description":"descriptionEvent","capacity":20,"startDate":"2024-10-03","endDate":"2024-10-13"}

    @GetMapping("/allevents")
    public ArrayList<Event> displayAll() {
        return events;
    }

    //----
    @PutMapping("/update/{ID}")
    public APIResponse update(@PathVariable String ID, @RequestBody Event event) {

        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getID().equalsIgnoreCase(ID)) {
                events.set(i, event);
                return new APIResponse(events.get(i).getID() + "  (Updated with)  " + event.toString());
            }
        }
        return new APIResponse("This ID: " + ID + " NOT FOUND");
    }

    //----------------------
    @DeleteMapping("/delete/{ID}")
    public APIResponse delete(@PathVariable String ID) {

        for (Event event : events) {
            if (event.getID().equalsIgnoreCase(ID)) {
                String info = event.getID();
                events.remove(event);
                return new APIResponse("Event with ID: " + info + " is deleted successfully");
            }
        }
        return new APIResponse("This ID" + ID + " NOT FOUND");
    }

    //---• Change capacity

    @PutMapping("/changeCapacity/{ID}/{capacity}")
    public APIResponse changeCapacity(@PathVariable String ID, @PathVariable int capacity) {
        for (Event event : events) {
            if (event.getID().equalsIgnoreCase(ID)) {
                event.setCapacity(capacity);
                return new APIResponse("Change Capacity with" + capacity + "Successfully");
            }
        }
        return new APIResponse("ID " + ID + " NOT FOUND");
    }

    //
    @GetMapping("/serach/{ID}")
    public Event search(@PathVariable String ID) {
        for (Event event : events) {
            if (event.getID().equalsIgnoreCase(ID))
                return event;
        }
        return null;
    }


}
