package github.user.sdk.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import androidx.fragment.app.DialogFragment
import github.user.sdk.R
import github.user.sdk.data.UserResponse
import github.user.sdk.databinding.UserDetailFragmentBinding
import github.user.sdk.extendsion.parcelable
import timber.log.Timber

class UserDetailFragment : DialogFragment() {
    private lateinit var user: UserResponse
    private lateinit var binding: UserDetailFragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen)
        user = arguments?.parcelable(USER_KEY) ?: UserResponse()
        Timber.d("$USER_DETAIL_TAG: $user")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UserDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.composeView.setContent {
            UserDetailScreen(user = user)
        }
    }

    @Composable
    private fun UserDetailScreen(user: UserResponse) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.white))
        ) {
            Text(text = user.login ?: "", fontSize = 20.sp)
        }
    }

    companion object {
        const val USER_DETAIL_TAG = "UserDetailFragment"
        const val USER_KEY = "user"
        fun getInstance(userResponse: UserResponse): UserDetailFragment {
            val fragment = UserDetailFragment()
            fragment.arguments = Bundle().apply {
                putParcelable(USER_KEY, userResponse)
            }
            return fragment
        }
    }
}