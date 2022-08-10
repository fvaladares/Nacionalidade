/*
 * Created/Adapted by fabricio on 10/08/2022 05:07
 * Last modified 10/08/2022 05:06
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

package br.pucminas.meunome.ui.iniciofragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import br.pucminas.meunome.R
import br.pucminas.meunome.databinding.FragmentInicioBinding
import br.pucminas.meunome.ui.infodialogfragment.InfoDialogFragment
import br.pucminas.meunome.ui.main.MainViewModel

class InicioFragment : Fragment(), MenuProvider {

    private var _binding: FragmentInicioBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInicioBinding.inflate(
            inflater,
            container,
            false
        )
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configurarListeners()
        prepararObservers()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        binding.etNome.text?.clear()
    }

    private fun prepararObservers() {
        configurarErroNome()
    }

    private fun configurarErroNome() {
        sharedViewModel.erroNome.observe(viewLifecycleOwner) {
            exibirMensagemErro(it)
        }
    }

    private fun exibirMensagemErro(erro: Boolean?) {
        binding.tilnome.error = if (erro == true) {
            getString(R.string.erro_nome)
        } else {
            null
        }
    }

    private fun navegarProximaTela(nome: String) {
        val action = InicioFragmentDirections
            .actionInicioFragmentToResultadoFragment(nome = nome)
        findNavController().navigate(action)

    }

    private fun configurarListeners() {
        configurarBtnListener()
    }

    private fun configurarBtnListener() {
        binding.btEnviar.setOnClickListener {
            enviarDados(binding.etNome.text.toString())
        }
    }

    private fun enviarDados(nome: String) {
        if (sharedViewModel.enviarDados(nome)) {
            navegarProximaTela(nome)
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.layout_menu, menu)
        val layout = menu.findItem(R.id.actionMenuInfo)
        layout.icon =
            ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_outline_info_24)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.actionMenuInfo -> {
                showInfo()
                return true
            }
            else -> false
        }
    }

    private fun showInfo() {
        InfoDialogFragment().show(
            (context as AppCompatActivity).supportFragmentManager,
            "infoFragment"
        )
    }
}