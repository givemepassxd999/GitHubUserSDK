package github.user.sdk.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import coil.compose.AsyncImage
import dagger.hilt.android.AndroidEntryPoint
import github.user.sdk.R
import github.user.sdk.data.UserResponse
import github.user.sdk.databinding.UserDetailFragmentBinding
import github.user.sdk.extendsion.parcelable
import github.user.sdk.viewmodel.UserDetailViewModel
import timber.log.Timber

@AndroidEntryPoint
class UserDetailFragment : DialogFragment() {

    private lateinit var user: UserResponse
    private lateinit var binding: UserDetailFragmentBinding
    private val viewModel: UserDetailViewModel by viewModels()
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
            UserDetailScreen(
                user = user,
                viewModel = viewModel
            )
        }
    }

    @Composable
    private fun UserDetailScreen(user: UserResponse, viewModel: UserDetailViewModel) {
        LaunchedEffect(key1 = Unit) {
            viewModel.fetchUserDetail(user.login ?: "")
        }
        val userDetail = viewModel.user.collectAsState().value
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.white))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .padding(start = 10.dp)
                            .size(100.dp)
                            .clip(CircleShape),
                        model = userDetail.avatarUrl ?: "",
                        contentDescription = null,
                    )
                    Text(text = userDetail.login ?: "", fontSize = 20.sp)
                    Text(text = userDetail.bio ?: "", fontSize = 20.sp)
                }
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp))
            Column(modifier = Modifier.padding(start = 10.dp)) {
                if (userDetail.login?.isNotEmpty() == true) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = colorResource(id = R.color.black)
                        )
                        Column {
                            Column(
                                modifier = Modifier.padding(10.dp),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = user.login ?: "", color = Color.Black
                                )
                                if (user.siteAdmin == true) {
                                    Box(
                                        modifier = Modifier.background(
                                            color = colorResource(
                                                id = R.color.color_4953d4
                                            ), shape = RoundedCornerShape(10.dp)
                                        )
                                    ) {
                                        Text(
                                            text = getString(R.string.staff),
                                            fontSize = 12.sp,
                                            modifier = Modifier.padding(
                                                vertical = 2.dp, horizontal = 10.dp
                                            ),
                                            color = Color.White
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                if (userDetail.location?.isNotEmpty() == true) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Place,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = colorResource(id = R.color.black)
                        )
                        Column {
                            Column(
                                modifier = Modifier.padding(10.dp),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = userDetail.location ?: "", color = Color.Black
                                )
                            }
                        }

                    }
                }
                if (userDetail.blog?.isNotEmpty() == true) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.link),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = colorResource(id = R.color.black)
                        )
                        Column {
                            Column(
                                modifier = Modifier.padding(10.dp),
                                verticalArrangement = Arrangement.Center
                            ) {
                                val annotatedString = buildAnnotatedString {
                                    pushStringAnnotation(
                                        tag = stringResource(R.string.url_tag),
                                        annotation = userDetail.blog ?: ""
                                    )
                                    withStyle(style = SpanStyle(color = colorResource(id = R.color.purple_700))) {
                                        append(userDetail.blog ?: "")
                                    }
                                }
                                ClickableText(
                                    text = annotatedString,
                                    onClick = {
                                        userDetail.blog?.let { url ->
                                            if (url.isNotEmpty()) {
                                                val intent =
                                                    Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                                startActivity(intent)
                                            }
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
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