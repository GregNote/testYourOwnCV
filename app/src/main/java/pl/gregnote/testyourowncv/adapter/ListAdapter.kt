package pl.gregnote.testyourowncv.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.gregnote.testyourowncv.R
import pl.gregnote.testyourowncv.models.Item
import java.lang.ref.WeakReference

class ListAdapter(
    private val itemsList: ArrayList<Item>,
    private val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {
    var lastSelectedPosition = -1
    var lastSelected = WeakReference<View>(null)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.row_item, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = itemsList.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = itemsList[position]
        holder.name.text = item.fieldName
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(item.fieldName)
            lastSelected.get()?.isSelected = false
            lastSelected = WeakReference(holder.background)
            lastSelectedPosition = itemsList.indexOf(item)
            holder.background.isSelected = true
        }
        holder.background.isSelected = position == lastSelectedPosition
    }

    class ListViewHolder(
        view: View,
        val background: View = view.findViewById(R.id.rowBackground),
        val name: TextView = view.findViewById(R.id.name)
    ) : RecyclerView.ViewHolder(view)
}
