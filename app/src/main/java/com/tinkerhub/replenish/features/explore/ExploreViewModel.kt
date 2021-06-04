package com.tinkerhub.replenish.features.explore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.javafaker.Faker
import com.tinkerhub.replenish.data.models.EventItem
import com.tinkerhub.replenish.data.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor() : ViewModel() {
    
    val eventsList = MutableLiveData<ArrayList<EventItem>>()
    val activitiesList = MutableLiveData<ArrayList<EventItem>>()
    val user = MutableLiveData<User>()
    
    init {
        val mockEventsList = arrayListOf<EventItem>()
        val mockActivitiesList = arrayListOf<EventItem>()
        val faker = Faker()
        
        user.value = User(
            _id = "staticid",
            firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            avatarUrl = faker.company().logo(),
            points = faker.random().nextInt(0, 10000),
            level = "Silver"
        )
        
        for (i in 1..10) {
            val mockItem = EventItem(
                _id = i,
                title = faker.book().title(),
                about = faker.lorem().sentence(200),
                mechanics = faker.lorem().sentence(100),
                rewardPoints = i * 5,
                location = faker.address().fullAddress(),
                organizer = faker.company().name(),
                coverPhotoUrl = faker.company().logo(),
                participantsCount = i * 10,
                hasJoined = (i % 2 == 0),
                earnedPoints = if (i % 2 == 0) (i * 5) * i else 0,
                maxAttemptsCount = i + 2,
                attemptsCount = if (i % 2 == 0) i else 0,
                startDate = "2021-05-${i}T02:18:50Z",
                endDate = "2021-06-${i + 2}T02:18:50Z",
                scheduleTime = "${i}:00 AM - ${i + 1}:00 PM"
            )
            
            mockEventsList.add(mockItem)
            mockActivitiesList.add(mockItem)
        }
        eventsList.value = mockEventsList
        activitiesList.value = mockActivitiesList
    }
}