package com.tinkerhub.replenish.features.explore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.javafaker.Faker
import com.tinkerhub.replenish.data.models.EventItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor() : ViewModel() {
    
    val eventsList = MutableLiveData<ArrayList<EventItem>>()
    val activitiesList = MutableLiveData<ArrayList<EventItem>>()
    
    init {
        val mockEventsList = arrayListOf<EventItem>()
        val mockActivitiesList = arrayListOf<EventItem>()
        val faker = Faker()
        for (i in 1..10) {
            mockEventsList.add(
                EventItem(
                    name = faker.name().fullName(),
                    organizer = faker.company().name(),
                    location = faker.address().city(),
                    eventDates = "AUG $i - AUG 30",
                    bannerImageUrl = faker.company().logo(),
                    description = faker.lorem().sentence(200),
                    participants = i * 10,
                    points = 10
                )
            )
            
            mockActivitiesList.add(
                EventItem(
                    name = faker.name().fullName(),
                    organizer = faker.company().name(),
                    location = faker.address().city(),
                    eventDates = "AUG $i - AUG 30",
                    bannerImageUrl = faker.company().logo(),
                    description = faker.lorem().sentence(200),
                    participants = i * 10,
                    points = 10
                )
            )
        }
        eventsList.value = mockEventsList
        activitiesList.value = mockActivitiesList
    }
}