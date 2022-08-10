/*
 * Created/Adapted by fabricio on 10/08/2022 05:07
 * Last modified 08/08/2022 19:05
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

package br.pucminas.meunome.ui.resultadofragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.pucminas.meunome.databinding.ItemViewOriginBinding
import br.pucminas.meunome.domain.entidade.Pais

class OrigemNomeAdapter(
    private val items: List<Pais>
) : RecyclerView.Adapter<OrigemNomeAdapter.OrigemNomeViewHolder>() {

    private var _binding: ItemViewOriginBinding? = null
    private val binding get() = _binding!!

    inner class OrigemNomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val pais = binding.tvPaisValue
        val probabilidade = binding.tvProbabilidadeValue
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrigemNomeViewHolder {
        _binding = ItemViewOriginBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return OrigemNomeViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: OrigemNomeViewHolder, position: Int) {
        val item = items[position]
        holder.apply {
            pais.text = item.countryId
            probabilidade.text = (item.probability * 100).toString() + " %"
        }
    }

    override fun getItemCount(): Int = items.size
}