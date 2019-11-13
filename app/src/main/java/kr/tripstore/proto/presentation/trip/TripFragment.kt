package kr.tripstore.proto.presentation.trip

import android.os.Bundle
import androidx.fragment.app.viewModels
import kr.tripstore.proto.R
import kr.tripstore.proto.databinding.FragmentTripBinding
import kr.tripstore.proto.extension.getViewModelFactory
import kr.tripstore.proto.presentation.base.DataBindingFragment

class TripFragment : DataBindingFragment<FragmentTripBinding>() {

    override val layoutResId: Int
        get() = R.layout.fragment_trip

    private val viewModel by viewModels<TripViewModel> { getViewModelFactory() }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.viewModel = viewModel.apply {

        }
        viewModel.start()
    }

    companion object {
        fun newInstance() = TripFragment()
    }

}
