/*
 * Created/Adapted by fabricio on 10/08/2022 05:07
 * Last modified 09/08/2022 07:53
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

package br.pucminas.meunome.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.pucminas.meunome.data.network.NationalizeApi
import br.pucminas.meunome.domain.entidade.RetornoApi
import kotlinx.coroutines.launch
import java.text.Normalizer
import java.util.regex.Pattern

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val _origemNome = MutableLiveData<RetornoApi?>()
    private val _erroNome = MutableLiveData<Boolean>()
    val origemNome: LiveData<RetornoApi?> = _origemNome
    val erroNome: LiveData<Boolean> = _erroNome

    private fun getNationalizeName(nome: String) {
        viewModelScope.launch {
            try {
                val listResults = NationalizeApi.retrofitService.getNationalize(nome)
                Log.d("PUC::Debug", listResults.toString())
                _origemNome.value = listResults
            } catch (e: Exception) {
                Log.d("PUC::Debug::", e.toString())
            }
        }
    }

    fun enviarDados(nome: String): Boolean {
        return if (nome.isBlank()) {
            _erroNome.value = true
            false
        } else {
            _erroNome.value = false
            getNationalizeName(removeAccents(nome))
            true
        }
    }

    /**
     * source: https://gasparbarancelli.com/post/removendo-acentuacoes-e-sinais-graficos-de-uma-string?lang=pt
     */
    private fun removeAccents(value: String): String {
        val normalizer: String = Normalizer.normalize(value, Normalizer.Form.NFD)
        val pattern: Pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+")
        return pattern.matcher(normalizer).replaceAll("")
    }
}