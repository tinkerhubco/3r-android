package com.tinkerhub.replenish.features

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinkerhub.replenish.data.models.EventItem
import com.tinkerhub.replenish.data.models.RewardItem
import com.tinkerhub.replenish.network.responses.ClaimPointsResponse
import com.tinkerhub.replenish.sources.activity.IActivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PartnerViewModel @Inject constructor(
    private val activityRepository: IActivityRepository
) : ViewModel() {
    
    var qrRewardInfo = listOf<String>()
    val claimPointsResult = MutableLiveData<ClaimPointsResponse>()
    val activityData = MutableLiveData<EventItem>()
    val voucherDetails = MutableLiveData<RewardItem>()
    val currentDate = MutableLiveData<String>()
    
    suspend fun sendActivityPoints(qrData: String) {
        val rewardData = claimActivityPoints(qrData)
        val voucherDetails = rewardData?.vouchers?.find {
            it._id == qrRewardInfo[2]
        }
        
        this.voucherDetails.value = voucherDetails
        getEventDetails(voucherDetails?.activityId ?: "")
        
        val date = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("MMMM dd yyyy, h:mm a", Locale.getDefault())
        currentDate.value = dateFormat.format(date)
    }
    
    private suspend fun claimActivityPoints(qrData: String): ClaimPointsResponse? {
        return try {
            this.qrRewardInfo = qrData.split(";")
            activityRepository.claimActivityPoints(
                activityId = qrRewardInfo[0],
                userId = qrRewardInfo[1],
                voucherId = qrRewardInfo[2]
            ).apply {
                claimPointsResult.value = this
            }
        } catch (exception: Exception) {
            null
        }
    }
    
    private suspend fun getEventDetails(activityId: String) {
        activityData.value = activityRepository.getActivity(activityId)
    }
}