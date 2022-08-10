/*
 * Created/Adapted by fabricio on 10/08/2022 05:07
 * Last modified 08/08/2022 14:41
 *
 * Copyright (C) 2022 Professor fabricio - PUC Minas Virtual
 *
 * Code based on a example created by Google Developer Training
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and limitations under the License.
 *
 */

package br.pucminas.meunome.ui.infodialogfragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import br.pucminas.meunome.R
import br.pucminas.meunome.databinding.FragmentInfoDialogBinding

class InfoDialogFragment : DialogFragment() {
    private var _binding: FragmentInfoDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoDialogBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepararBtnListener()
    }

    private fun prepararBtnListener() {
        binding.btnEncerrar.setOnClickListener {
            configurarAcaoBotao()
        }
    }

    private fun configurarAcaoBotao() {
        dismiss()
    }

    override fun onResume() {
        super.onResume()
        val params = dialog?.window?.attributes
        params?.width = ViewGroup.LayoutParams.MATCH_PARENT
        params?.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog?.window?.attributes = params
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setTitle(getString(R.string.sobre_o_app))

        return dialog
    }
}